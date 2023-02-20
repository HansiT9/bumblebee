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
    })
    .fail(function (error) {
      alert("Login failed: " + error.status);
    });
});
