<html>
<head>
<title>Application Level Log4j Demo In JBoss AS7</title>
</head>
<body>

      <form action="TestServlet" method="Get">
           Choose Log Level : 
                           <select name="logLevel">
                              <option>TRACE</option>
                              <option>DEBUG</option>
                              <option>WARN</option>
                              <option selected="true">INFO</option>
                           </select>
                           <BR>
           Enter Mesage to be logged: <input type="textarea" name="logMessage"/> <BR>
           <input type="submit" name="submit" value="log the message"/> 
      </form>
      
</body>
</html>

