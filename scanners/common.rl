%%{
  machine common;
  
  action nl{
    notifyNewline();
  }

  action code{
    notifyCode();
  }

  action comment{
    notifyComment();
  }

  action begin_define_match{
    beginDefineMatch();
  }

  action end_define_match{
    endDefineMatch();
  }

  action begin_try_match{
    beginTryMatch();
  }

  spaces = [\t ]+; 
  newline = ('\r\n' | '\n' | '\f' | '\r' when { p+1 < pe && data[p+1] != '\n' }) %nl;
  nonnewline = any - [\r\n\f];
  text_with_newlines = (newline %nl) | nonnewline;
    
  escape_seq = '\\' nonnewline;
  string_char = (nonnewline - '\\') | escape_seq;
  string_literal_sq = '\'' string_char* :>> '\'' @code;
  string_literal_dq = '"' string_char* :>> '"' @code;
  string_literal = string_literal_sq | string_literal_dq;
  
}%%  
