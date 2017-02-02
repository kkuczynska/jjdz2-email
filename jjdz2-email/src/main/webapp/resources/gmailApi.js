// Your Client ID can be retrieved from your project in the Google
// Developer Console, https://console.developers.google.com
var CLIENT_ID = '569832515580-0fdmssh0p1n5dafr11apkvf2mh61gero.apps.googleusercontent.com';

var SCOPES = ['https://www.googleapis.com/auth/gmail.readonly'];

function checkAuth() {
    gapi.auth.authorize(
        {
            'client_id': CLIENT_ID,
            'scope': SCOPES.join(' '),
            'immediate': true
        }, handleAuthResult);
}

function handleAuthResult(authResult) {
    var numberOfGmails = document.getElementById("numberOfGmails");
    var authorize = document.getElementById("authorize");
    if (authResult && !authResult.error) {
        numberOfGmails.style.display = 'inline';
        authorize.style.display = 'none';
        loadGmailApi();
    }
    else {
        authorize.style.display = 'inline';
        numberOfGmails.style.display = 'none';
    }
}

function handleAuthClick(event) {
    gapi.auth.authorize({
        client_id : CLIENT_ID,
        scope : SCOPES,
        immediate : false
        }, handleAuthResult);
    return false;
}

function loadGmailApi() {
   gapi.client.load('gmail', 'v1', countMessages);
}

function countMessages() {
    var request = gapi.client.gmail.users.labels.get({
        'id': 'INBOX',
        'userId': 'me'
    });

    request.execute(function (resp) {
        document.getElementById('count').innerHTML = resp.messagesUnread;
    });
}

$(document).ready(function(){
    $(".body").click(function(){
        checkAuth();
        countMessages();
    });

});