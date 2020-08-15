<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <script src="https://use.fontawesome.com/e732d6cbfa.js"></script>
    <title>Dirtbag Dictionary | Results</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="head-top">
                <h1>dirtbag dictionary</h1>
            </div>
            <div class="head-bot">
                <form method="GET" action="SearchServlet">
                    <input type="text" name="query" placeholder="Type any word...">
                    <button type="submit"><i class="fa fa-search" aria-hidden="true" style="font-size:25px; color:#dadada;"></i></button>
                </form>
                <form method="POST" action="AlphaListServlet">
                	<button type=submit><i class="fa fa-sort-alpha-asc" aria-hidden="true" style="font-size:25px; color:#dadada;"></i></button>
                </form>
                <form method="GET" action="RandomServlet">
                	<button type=submit><i class="fa fa-random" aria-hidden="true" style="font-size:25px; color:#dadada;"></i></button>
                </form>
            </div>
        </div>
    </header>
    <section class="homepage">
        <div class="spotlight">
        	<% 
        		Map<String, String> wordMap = (HashMap<String,String>) session.getAttribute("wordMap");
        		Iterator it = wordMap.entrySet().iterator();
				
        	    while (it.hasNext()) {
        	        Map.Entry pair = (Map.Entry)it.next();
        	 
			%>
				<div class="result">
					<h3><%=pair.getKey() %></h3>
					<p><%=pair.getValue() %> </p>
				</div>
			<%
			 		it.remove();
				}
			%>
        </div>
    </section>
</body>
</html>