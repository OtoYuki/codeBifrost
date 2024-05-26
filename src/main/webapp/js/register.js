var questions = [
  { question: "Enter your username?" },
  { question: "Enter your first name?" },
  { question: "Enter your last name?" },
  { question: "What's your email?", pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/ },
  { question: "Create your password", type: "password" },
  { question: "Enter your phone number?", pattern: /^[0-9]{10}$/ }, // adjust the pattern to match your requirements
];

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

  function putQuestion() {
    inputLabel.innerHTML = questions[position].question;
    inputField.type = questions[position].type || "text";
    if (inputField.type === "file") {
      inputField.addEventListener("change", function () {
        questions[position].answer = this.files[0];
      });
    } else {
      inputField.value = questions[position].answer || "";
      inputField.focus();
    }

    progress.style.width = (position * 100) / questions.length + "%";

    previousButton.className = position
      ? "ion-android-arrow-back"
      : "ion-person";

    showCurrent();
  }

  function validate() {
    var validateCore = function () {
      if (inputField.type === "file") {
        return inputField.files.length > 0;
      } else {
        return inputField.value.match(questions[position].pattern || /.+/);
      }
    };

    if (!questions[position].validate)
      questions[position].validate = validateCore;

    if (!questions[position].validate())
      wrong(inputField.focus.bind(inputField));
    else
      ok(function () {
        if (questions[position].done) questions[position].done();
        else questions[position].answer = inputField.value;

        ++position;

        if (questions[position]) hideCurrent(putQuestion);
        else
          hideCurrent(function () {
            register.className = "close";
            progress.style.width = "100%";

            var xhr = new XMLHttpRequest();
            xhr.open("POST", contextPath + "/registeruser" , true);
            xhr.onreadystatechange = function() {
              if (this.readyState == 4 && this.status == 200) {
                if (this.responseText.includes("Successfully Registered!")) {
                  window.location.href = "../pages/login.jsp";
                } else if (this.responseText.includes("An unexpected server error occurred.")) {
                  window.location.href = "../pages/register.jsp";
                  alert("Username or Email or Phone Number already registered"); 
                } else if (this.responseText.includes("Please correct the form data.")) {
                  window.location.href = "../pages/register.jsp";
                  alert("Username or Email or Phone Number already registered");
                }
              }
            };
            var formData = new FormData();
            formData.append("userId", 0);
            formData.append("username", questions[0].answer);
            formData.append("firstName", questions[1].answer);
            formData.append("lastName", questions[2].answer);
            formData.append("email", questions[3].answer);
            formData.append("password", questions[4].answer);
            formData.append("phoneNumber", questions[5].answer);
            formData.append("isAdmin", 0);
            formData.append("profilePicture", null);
            xhr.send(formData);

            onComplete();
          });
      });
  }

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
    for (var i = 0; i < 6; i++)
      setTimeout(transform, tTime * i, ((i % 2) * 2 - 1) * 20, 0);
    setTimeout(transform, tTime * 6, 0, 0);
    setTimeout(callback, tTime * 7);
  }
})(questions, onComplete);