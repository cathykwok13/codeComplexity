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
           McCabe Cyclomatic Complexity - http://www.chambers.com.au/glossary/mc_cabe_cyclomatic_complexity.php
           Using this method:
                      http://www.klocwork.com/products/documentation/current/McCabe_Cyclomatic_Complexity
                      
           Cyclomatic complexity may be extended to a program with multiple exit points; in this case it is equal to:

                       π − s + 2,

           where π is the number of decision points in the program, and s is the number of exit points.
           
    
    
2. DEPENDENCY /COUPLING
           http://en.wikipedia.org/wiki/Coupling_%28computer_programming%29

           Measured as follows:
                      http://blogs.msdn.com/b/zainnab/archive/2011/05/25/code-metrics-class-coupling.aspx

