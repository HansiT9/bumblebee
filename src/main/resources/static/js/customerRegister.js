$(document).ready(function () {
  var timeoutId;

  $("#email").on("input", function () {
    const email = $(this).val();
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    clearTimeout(timeoutId);
    timeoutId = setTimeout(function () {
      if (email !== "" && pattern.test(email)) {
        $.get("http://localhost:8080/auth/customer/validate", {
          email: email,
        })
          .done(function (data) {})
          .fail(function () {
            alert("Email Already Exist!");
          });
      }
    }, 1000);
  });
});

$("#form-reg").submit(function (event) {
  event.preventDefault();

  const fullName = $("#fullName").val();
  const dob = $("#dob").val();
  const email = $("#email").val();
  const password = $("#password").val();

  $.ajax({
    url: "http://localhost:8080/auth/customer/register",
    method: "POST",
    data: JSON.stringify({
      fullName: fullName,
      dob: dob,
      email: email,
      password: password,
    }),
    contentType: "application/json",
  })
    .done(function (data) {
      alert("Registration successful: " + data.status);
      window.location.href = "http://localhost:8080/Registration-Success";
    })
    .fail(function (error) {
      alert("Registration failed: " + error.status);
    });
});
