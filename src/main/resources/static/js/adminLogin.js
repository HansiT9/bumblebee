$(document).ready(function () {
  if (sessionStorage.getItem("loggedIn") === true) {
    location.href = "http://localhost:8080/admin_center";
  }
});
$("#login-form").submit(function (event) {
  event.preventDefault();

  const email = $("#email").val();
  const password = $("#pwd").val();

  $.ajax({
    url: "http://localhost:8080/auth/admin/login",
    method: "POST",
    data: JSON.stringify({
      email: email,
      password: password,
    }),
    contentType: "application/json",
  })
    .done(function (data) {
      console.log("Login successful:", data);
      sessionStorage.setItem("loggedIn", true);
      sessionStorage.setItem("email", email);
      window.location.href = "http://localhost:8080/admin_center";
    })
    .fail(function (error) {
      console.error("Login failed:", error);
      alert("Email or Password incorrect. Please try again!");
    });
});
