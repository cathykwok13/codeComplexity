codeComplexity
==============

This tool analyzes the complexity of code and how it relates to bugs fixed within that code. 
The visualization shows the lifetime of a project and highlights where and when bugs are fixed, 
the complexity of the code at the time the bug is identified, and the complexity of the code 
after the bug has been fixed. Code complexity will be shown at multiple levels of granularity – 
per method, class, and package. To quantify complexity, we are using a combination of cyclomatic
complexity and the number of method invocations that the given piece of code makes.

The two metrics measured are:

1. Complexity - using a control flow chart model
           http://en.wikipedia.org/wiki/Cyclomatic_complexity 
           
    M = E − N + 2P
    
2. DEPENDENCY /COUPLING
           http://en.wikipedia.org/wiki/Coupling_%28computer_programming%29

           Measured as follows:
                      http://blogs.msdn.com/b/zainnab/archive/2011/05/25/code-metrics-class-coupling.aspx

