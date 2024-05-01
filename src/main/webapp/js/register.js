/**
 *
 */

var questions = [
  { question: "Enter your username?" },
  { question: "Enter your first name?" },
  { question: "Enter your last name?" },
  { question: "What's your email?", pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/ },
  { question: "Create your password", type: "password" },
  { question: "Enter your phone number?", pattern: /^[0-9]{10}$/ }, // adjust the pattern to match your requirements
];
/*
    do something after the questions have been answered
  */
var onComplete = function () {
  var h1 = document.createElement("h1");
  h1.appendChild(
    document.createTextNode(
      "Thanks " + questions[0].answer + " for registering your Account."
    )
  );
  setTimeout(function () {
    register.parentElement.appendChild(h1);
    setTimeout(function () {
      h1.style.opacity = 1;
    }, 50);
  }, 1000);
};

(function (questions, onComplete) {
  var tTime = 100; // transition transform time from #register in ms
  var wTime = 200; // transition width time from #register in ms
  var eTime = 1000; // transition width time from inputLabel in ms

  // init
  // --------------
  if (questions.length == 0) return;

  var position = 0;

  putQuestion();

  forwardButton.addEventListener("click", validate);
  inputField.addEventListener("keyup", function (e) {
    transform(0, 0); // ie hack to redraw
    if (e.keyCode == 13) validate();
  });

  previousButton.addEventListener("click", function (e) {
    if (position === 0) return;
    position -= 1;
    hideCurrent(putQuestion);
  });

  // functions
  // --------------

  // load the next question
  function putQuestion() {
    inputLabel.innerHTML = questions[position].question;
    inputField.type = questions[position].type || "text";
    inputField.value = questions[position].answer || "";
    inputField.focus();

    // set the progress of the background
    progress.style.width = (position * 100) / questions.length + "%";

    previousButton.className = position
      ? "ion-android-arrow-back"
      : "ion-person";

    showCurrent();
  }

  // when submitting the current question
  function validate() {
    var validateCore = function () {
      return inputField.value.match(questions[position].pattern || /.+/);
    };

    if (!questions[position].validate)
      questions[position].validate = validateCore;

    // check if the pattern matches
    if (!questions[position].validate())
      wrong(inputField.focus.bind(inputField));
    else
      ok(function () {
        // execute the custom end function or the default value set
        if (questions[position].done) questions[position].done();
        else questions[position].answer = inputField.value;

        ++position;

        // if there is a new question, hide current and load next
        if (questions[position]) hideCurrent(putQuestion);
        else
          hideCurrent(function () {
            // remove the box if there is no next question
            register.className = "close";
            progress.style.width = "100%";

            // Send a POST request to your servlet
            var xhr = new XMLHttpRequest();
            xhr.open("POST", contextPath + "/register" , true);
            xhr.setRequestHeader(
              "Content-Type",
              "application/x-www-form-urlencoded"
              );
              xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                  // Check if the response contains the success message
                  if (this.responseText.includes("Successfully Registered!")) {
                    // Redirect to the login page
                    window.location.href = "../pages/login.jsp";
                  } else if (this.responseText.includes("Registration Failed")) {
                    // Show an alert to the user
                    alert("Registration failed. Please try again.");
                  } else if (this.responseText.includes("Server Error")) {
                    // Show an alert to the user
                    alert("A server error occurred. Please try again later.");
                  }
                }
              };
            xhr.send(
              "userId=" +
                encodeURIComponent(0) +
                "&username=" +
                encodeURIComponent(questions[0].answer) +
                "&firstName=" +
                encodeURIComponent(questions[1].answer) +
                "&lastName=" +
                encodeURIComponent(questions[2].answer) +
                "&email=" +
                encodeURIComponent(questions[3].answer) +
                "&password=" +
                encodeURIComponent(questions[4].answer) +
                "&phoneNumber=" +
                encodeURIComponent(questions[5].answer) +
                "&isAdmin=" +
                encodeURIComponent(0)
            );
            onComplete();
          });
      });
  }

  // helper
  // --------------

  function hideCurrent(callback) {
    inputContainer.style.opacity = 0;
    inputLabel.style.marginLeft = 0;
    inputProgress.style.width = 0;
    inputProgress.style.transition = "none";
    inputContainer.style.border = null;
    setTimeout(callback, wTime);
  }

  function showCurrent(callback) {
    inputContainer.style.opacity = 1;
    inputProgress.style.transition = "";
    inputProgress.style.width = "100%";
    setTimeout(callback, wTime);
  }

  function transform(x, y) {
    register.style.transform = "translate(" + x + "px ,  " + y + "px)";
  }

  function ok(callback) {
    register.className = "";
    setTimeout(transform, tTime * 0, 0, 10);
    setTimeout(transform, tTime * 1, 0, 0);
    setTimeout(callback, tTime * 2);
  }

  function wrong(callback) {
    register.className = "wrong";
    for (
      var i = 0;
      i < 6;
      i++ // shaking motion
    )
      setTimeout(transform, tTime * i, ((i % 2) * 2 - 1) * 20, 0);
    setTimeout(transform, tTime * 6, 0, 0);
    setTimeout(callback, tTime * 7);
  }
})(questions, onComplete);
