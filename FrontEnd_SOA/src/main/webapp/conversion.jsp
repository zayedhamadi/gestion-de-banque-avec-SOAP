<%@page import="java.util.ArrayList"%>
<%@page import="service.Compte"%>
<%@page import="service.BanqueWS"%>
<%@page import="service.BanqueService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="css/style.css">
  <!-- <style type="text/css">
    select{
	width:200px;
	cursor:pointer;
	margin:15px 0px;
	height:25px;
	padding:3px;
}
select option{
pading:4px;
cursor:pointer;
}
    
    
    </style> -->  
    <%
        BanqueService proxy = new BanqueWS().getBanqueServicePort();
        int code = (Integer) session.getAttribute("codecin");
        ArrayList<String> currency = new ArrayList<>();
        currency.add("EUR"); // Euro
        currency.add("USD"); // Dollar
        currency.add("TND"); // Tunisian Dinar
        Object res = request.getAttribute("result");
    %>
</head>
	<body>
	    <nav class="navbar">
	        <div class="logo">
	            <img src="./image/banque2.jpg" alt="Logo">
	        </div>
	        <ul class="nav-links">
	            <li><a href="home.jsp">Accueil</a></li>
	            <li><a href="versement.jsp">versement</a></li>
	            <li><a href="retrait.jsp">retrait</a></li>
	            <li><a href="transfert.jsp">transfert</a></li>
	            <li><a href="conversion.jsp">conversion</a></li>
	            <li><a href="historique.jsp">historique</a></li>
	        </ul>
	        
	        <div class="mama"><a class="logout"  href="deconnecter.DAO">Deconnecter</a></div>
	    </nav>
	    <div class="content">
	        <div class="balance">
        <form action="conversion.dao" method="post">
            <label for="currency1">Choisir Devise 1:</label>
            <select name="currency1">
                <% for (String c : currency) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>
            <br>
            <label for="currency2">Select Devise 2:</label>
            <select name="currency2">
                <% for (String c : currency) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select><br>
            <br>
            <input type="text" name="amount" placeholder="amount"><br>
            <input type="submit" value="convert">
        </form>
    </div>
	        <div <%if(res!=null){ %>>
	        <h1>Your result is</h1>
	        <%=res %>
	        
	        </div>
	        <%} %>
	    </div>
	</body>
	</html>