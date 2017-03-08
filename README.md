# XProcessor

## Setup

Using antlr-4.6-complete.jar

<pre>
export CLASSPATH=".:/Users/caleb/ProjHW/XProcessor/lib/antlr-4.6-complete.jar:$CLASSPATH"
alias antlr4='java -jar /Users/caleb/ProjHW/XProcessor/lib/antlr-4.6-complete.jar'
alias grun='java org.antlr.v4.gui.TestRig'
</pre>

## Compile

<pre>
antlr4 XPath.g4 -visitor
javac XPath*.java
antlr4 XQuery.g4 -visitor
java XQuery*.java
</pre>