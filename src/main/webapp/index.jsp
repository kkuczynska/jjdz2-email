<html>
  <head>

        <script src="resources/jquery/jquery-3.1.1.min.js"></script>
        <script src="count.js"></script>
        <script src="https://apis.google.com/js/client.js?onload=checkAuth"></script>
  </head>
  <body>
  <p>siema
  </p>
    <div id="authorize-div">
      <span>Authorize access to Gmail API</span>
      <!--Button for the user to click to initiate auth sequence -->
      <button id="authorize-button" onclick="handleAuthClick(event)">
        Authorize
      </button>
    </div>
    <div> <button id="authorize" onclick="listMessages()">
                 list
               </button>

               <button id="authorized" onclick="countMessages()">
                                count
                              </button>
    </div>
        <form action="keywords.jsp">
        <input class="btn btn-warning" type="submit" value="sssss" name="sss">
        </form>

    <pre id="output"></pre>
    <pre id="count"></pre>
    <pre id="content"></pre>
  </body>
</html>