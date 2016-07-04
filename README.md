[![Build Status](https://travis-ci.org/mpujari/ohcount4j.svg?branch=master)](https://travis-ci.org/mpujari/ohcount4j)

# Overview

Ohcount4j is a ragel/java application for inspecting source by detecting languages and counting lines of code.  For example,
```
./ohcount4j src/test/src-code

Ohcount4j Line Count Summary
Language                  Files       Code    Comment  Comment %      Blank      Total
------------------------  -----  ---------  ---------  ---------  ---------  ---------
C                             4         33         15      31.3%         13         61
HaXe                          1         32         62      66.0%          7        101
HTML                          3         29          3       9.4%          2         34
MetaPost                      1         26          8      23.5%          8         42
OpenGL Shading Language       1         25         12      32.4%          6         43
TeX/LaTeX                     1         19          1       5.0%          0         20
TypeScript                    1         15          3      16.7%          4         22
MetaFont                      1         13          4      23.5%          5         22
COBOL                         1         13          1       7.1%          2         16
BlitzMax                      1          9         12      57.1%         11         32
ChaiScript                    1          8          7      46.7%          2         17
ClearSilver                   2          8          1      11.1%          0          9
CoffeeScript                  1          7          9      56.3%          7         23
Haml                          1          7          8      53.3%          1         16
Ruby                          1          4          0       0.0%          0          4
JavaScript                    2          4          0       0.0%          0          4
CSS                           1          1          0       0.0%          0          1
------------------------  -----  ---------  ---------  ---------  ---------  ---------
Total                        17        253        146      36.6%         68        467

```

## Parameters
The following describes the parameter options for ohcount4j:
```
[file] : target
-a    : show annotated source code
-d    : show detected file types only
-h    : display this message
-l    : show supported languages
-s    : show line count summary (default)
```

## Supported Platforms
The currently supported platforms are:
* Ubuntu 14.04 LTS
* Mac OS X 10.9.x (Dev environment)

## Licensing

Ohcount4j is licensed under [Apache 2](http://www.apache.org/licenses/LICENSE-2.0).  See LICENSE in the root directory for details.

## Supported Languages

```
./ohcount4j -l

Ohcount4j supported languages

----------------------------------
  Name              Nice name         
----------------------------------
  actionscript      ActionScript      
  ada               Ada               
  aspx_csharp       ASP.NET (C#)      
  aspx_vb           ASP.NET (VB)      
  assembly          Assembly          
  augeas            Augeas            
  autoconf          Autoconf          
  automake          Automake          
  awk               Awk               
  bat               Windows Batch     
  bfpp              Brainfuck++       
  binary            Binary            
  blitzmax          BlitzMax          
  boo               Boo               
  brainfuck         Brainfuck         
  c                 C                 
  chaiscript        ChaiScript        
  classic_basic     Classic BASIC     
  clearsilver       ClearSilver       
  clojure           Clojure           
  cobol             COBOL             
  coffeescript      CoffeeScript      
  coldfusion        ColdFusion        
  cpp               C++               
  cmake             CMake             
  csharp            C#                
  coq               Coq               
  css               CSS               
  cuda              CUDA              
  d                 D                 
  dylan             Dylan             
  dcl               DCL               
  ebuild            Ebuild            
  ec                eC                
  ecmascript        ECMAScript        
  eiffel            Eiffel            
  elixir            Elixir            
  emacslisp         Emacs Lisp        
  erlang            Erlang            
  factor            Factor            
  exheres           Exheres           
  forth             Forth             
  fortranfixed      Fortran (Fixed-Format)
  fortranfree       Fortran (Free-Format)
  fsharp            F#                
  genie             Genie             
  glsl              OpenGL Shading Language
  golang            Go                
  groovy            Groovy            
  haml              Haml              
  haxe              HaXe              
  html              HTML              
  haskell           Haskell           
  idl_pvwave        IDL/PV-WAVE/GDL   
  jam               Jam               
  java              Java              
  javascript        JavaScript        
  jsp               JSP               
  kotlin            Kotlin            
  limbo             Limbo             
  lisp              Lisp              
  logtalk           Logtalk           
  lua               Lua               
  make              Make              
  mathematica       Mathematica       
  matlab            Matlab            
  metapost          MetaPost          
  metafont          MetaFont          
  modula2           Modula 2          
  modula3           Modula 3          
  objective_c       Objective-C       
  ocaml             OCaml             
  octave            Octave            
  pascal            Pascal            
  perl              Perl              
  php               Php               
  puppet            Puppet            
  prolog            Prolog            
  python            Python            
  r                 R                 
  rebol             REBOL             
  rexx              Rexx              
  ruby              Ruby              
  scala             Scala             
  swift             Swift             
  scheme            Scheme            
  shell             Shell             
  smalltalk         Smalltalk         
  sql               SQL               
  structured_basic  Structured Basic  
  tcl               Tcl               
  tex               TeX/LaTeX         
  typescript        TypeScript
  unknown           Unknown           
  vb                VisualBasic       
  vbscript          VBScript          
  vimscript         Vimscript         
  xml               XML               
  xmlschema         XML Schema        
  xslt              XSL Transformation
```

# Prerequisite Software

1. Install **Eclispe**
2. Install **ragel** (minimum: 6.8, recommended 6.9).  For example, to install on Mac OS X via [homebrew](https://github.com/Homebrew/homebrew/blob/master/Library/Formula/ragel.rb):

     `brew install ragel`
3. Install **TestNG** plugin in Eclipse (see below)

# Build Configuration Steps

1. Checkout ohcount4j project

    `cd git-clone-dir`

    `git clone https://github.com/mpujari/ohcount4j.git`

2. Before importing the ohcount4j project into Eclipse, run the gradle build to create the generated ragel files used by the project.  The gradle script supports the ability to run specifics task for cleaning, regenerating the source, and running tests.  See the build.gradle file for details.

    `./gradlew clean build`

3. Create a workspace (Eclipse will require you do this when you start)

4. Install [TestNG plugin](http://testng.org/doc/download.html)
  * Go to Help -> Install New Software -> Add
  * Click **Add**
  * Enter Name -> TestNG, Location -> http://beust.com/eclipse
  * Click **Next**
  * Check TestNG and follow the instructions

5. Import this ohcount4j project in Eclipse
  * Click File -> Import
  * Choose Gradle -> Gradle Project
  * Click **Next**
  * Select root dir, e.g. *git-clone-dir/ohcount4j*
  * Click **Build Model**
  * In Projects: section, you should see ohlcount4j (*git-clone-dir/ohcount4j*) checked.
  * Click **Finish** (do not check Copy Projects into workspace)

# Project Layout

```
scanners - ragel scanner definitions. Because it produces Java,
           name of the file should match scanner class name and it should
           have net.ohloh.ohcount4j.scanner package definition on top.  
           Also, the extension should be .java.rl.
           See ExampleScanner.java.rl.
src - source directory
test - test source directory
src/generated/java - generated scanner sources
           generateScanSources target invokes ragel command on each file in scanners directory
           and output goes into src/generated/java/com/blackducksoftware/ohcount4j/scan
lib - libraries
```
