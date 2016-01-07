rem
rem This batch file runs the Jiffie JUnit tests. Please change the value of
rem the jiffie.junit.data dir property to the correct location for your
rem installation of jiffie before running the batch file
rem
java -Djiffie.junit.datadir=c:/java/jiffie/junit -cp jiffie.jar;jacob.jar;jaxen-1.1.1.jar;junit.jar junit.textui.TestRunner net.sf.jiffie.JiffieTest