$(document).ready(function () {
  // Validate email with database
  let timeoutIdEmail;
  $("#email").on("input", function () {
    const email = $(this).val();
    const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    clearTimeout(timeoutIdEmail);
    timeoutIdEmail = setTimeout(function () {
      if (email !== "" && patternEmail.test(email)) {
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

  // validate age of Customer
  let timeoutIdDob;
  $("#dob").on("input", function () {
    const dob = $(this).val();
    const patternDob = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
    clearTimeout(timeoutIdDob);
    timeoutIdDob = setTimeout(function () {
      if (dob !== "" && patternDob.test(dob)) {
        // Current date
        const currentDate = new Date();
        // Convert date of birth to date object
        const dobDate = new Date(dob);
        // Calculate the age based on the current date and the DOB value
        const age = currentDate.getFullYear() - dobDate.getFullYear();
        // Check if the age is less than 18
        if (age < 18) {
          $("#installment").hide();
        } else {
          $("#installment").show();
        }
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
  let $checkedRadio = $("input[type=radio]:checked");

  let installmentPlan = $checkedRadio.attr("id");
  if (installmentPlan === undefined) {
    installmentPlan = "n/a";
  }

  $.ajax({
    url: "http://localhost:8080/auth/customer/register",
    method: "POST",
    data: JSON.stringify({
      customerFullName: fullName,
      dob,
      customerEmail: email,
      password,
      installmentPlan: installmentPlan,
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
