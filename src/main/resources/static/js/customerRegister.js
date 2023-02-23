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
            $("#email").val("");
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
      customerFullName: fullName,
      dob,
      customerEmail: email,
      password,
    }),
    contentType: "application/json",
  })
    .done(function () {
      alert("Registration successful");
      window.location.href = "http://localhost:8080/Registration-Success";
    })
    .fail(function () {
      alert("Registration failed! Please try again");
    });
});
