var connected = false;
var socket = null;
var user = new Object();
localStorage.setItem('user', user);

startWebSocket();

function startWebSocket() {
	socket = new SockJS('http://localhost:8080/myapp');

	socket.onopen = function() {
	  connected = true;
	  console.log("Connection to server made successfully.");
	};

	socket.onclose = function() {
	  connected = false;
	  console.log("Connection to server disconnected.");
	};

	socket.onmessage = function(payload) {"username"
	  var json = JSON.parse(payload.data);
	  switch(json.type) {

	    /* Result of trying to login */
	    case "loginResponse":
	      if(json.authenticated == "true") {
	        authenticated = true;
	        user.username = json.username;
	        user.token = json.token;
	        handlePostLogin();
	        console.log("Successfully, logged in.");
	      } else {
	        authenticated = false;
	        console.log("Failed to login: " + json.reason);
	      }
	    break;

	    /* List of contacts */
	    case "contacts":
	      console.log("Received contacts");
	      displayContacts(json);
	    break;

	    /* List of open chats */
	    case "chat":
	      console.log("Received chat");
	      displayChat(json);
	    break;

	    default:
	      console.log("Unhandled type received: " + json.type);
	    break;
	  }
	};
}

function requestContacts() {
	console.log("requesting contacts");
	var obj = new Object();
  obj.type = "contactsRequest";
  obj.username  = user.username;
  obj.password = user.token;
  send(obj);
}

function displayContacts(json) {
  var contacts = json.contacts;
  var i;
  for (i = 0; i < contacts.length; i++) {
    var contact = contacts[i];

    var html = '<div class="contact" onclick="requestChat(' + contact.id + ')">';
    html += '<div class="profilePicture">' +  contact.profilePicUrl + '</div>';
    html += '<div class="contactName">' +  contact.firstName + " " + contact.lastName + '</div>';
    html += '<div class="status">' +  contact.status + '</div>';
    html += '<div class="online">' +  contact.online + '</div>';
    html += '</div>';

    document.getElementById("contacts").innerHTML += html;
  }
}

function requestChat(id) {
  //loadPage("chat.html");

  var chatRequest = new Object();
  chatRequest.type = "chatRequest";
  chatRequest.id = id.toString();
  chatRequest.username = user.username;
  chatRequest.token = user.token;
  send(chatRequest);
}

function displayChat(json) {
  var chat = json.chats[0]; //TODO: this should not be send as array in server
  var i;
  for (i = 0; i < chat.messages.length; i++) {
    var message = chat.messages[i];
    var html = '<div class="message">';
    html += '<div class="timestamp">' + message.timestamp + '</div>';
    html += '<div class="message">' + message.message + '</div>';
    html += '<div class="read">' + message.read + '</div>';
    html += '</div>';
    document.getElementById("chat").innerHTML += html;
  }
}

function displayRecentChats(json) {
  var chats = json.chats;
  var i;
  for (i = 0; i < chats.length; i++) {
    var chat = chats[i];
    var j;
    for (j = 0; j < chat.messages.length; j++) {
      var message = chat.messages[i];

      var html = '<div class="chat">';
      html += '<div class="profilePicture"></div>';

      document.getElementById("chats").innerHTML = xmlHttp.responseText;

    }
  }
}

function login() {
  var obj = new Object();
  obj.type = "login";
  obj.username  = document.getElementById("username").value;
  obj.password = document.getElementById("password").value;
  send(obj);
}

function handlePostLogin() {
	window.location = "main.html";
}

function send(obj) {
  socket.send(JSON.stringify(obj));
  console.log("sent: " + JSON.stringify(obj));
}
