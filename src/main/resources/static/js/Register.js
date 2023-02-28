$(document).ready(function () {
  if (sessionStorage.getItem("loggedIn") === true) {
    location.href = "http://localhost:8080/admin_center";
  }

  // default view
  $("#fullNameGroup, #dobGroup, #installment").hide();
  $("#fullName, #dob").prop("disabled", true);
  $("#emailLabel").text("Admin Email:");
  $("#passwordLabel").text("Admin Password:");
  sessionStorage.setItem("type", "admin");

  // switch between admin and customer register
  $("#switchOption").change(function () {
    if ($(this).is(":checked")) {
      $("#fullNameGroup, #dobGroup, #installment").show();
      $("#fullName, #dob").prop("disabled", false);
      $("#emailLabel").text("Customer Email:");
      $("#passwordLabel").text("Customer Password:");
      sessionStorage.setItem("type", "customer");
    } else {
      $("#fullNameGroup, #dobGroup, #installment").hide();
      $("#fullName, #dob").prop("disabled", true);
      $("#emailLabel").text("Admin Email:");
      $("#passwordLabel").text("Admin Password:");
      sessionStorage.setItem("type", "admin");
    }
  });

  // Validate email with database
  let timeoutIdEmail;
  $("#email").on("input", function () {
    const email = $(this).val();
    const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    clearTimeout(timeoutIdEmail);
    timeoutIdEmail = setTimeout(function () {
      if (email !== "" && patternEmail.test(email)) {
        if (sessionStorage.getItem("type") === "customer") {
          $.get("http://localhost:8080/auth/customer/validate", {
            email: email,
          })
            .done(function (data) {})
            .fail(function () {
              alert("Email Already Exist!");
              $("#email").val("");
            });
        }
        if (sessionStorage.getItem("type") === "admin") {
          $.get("http://localhost:8080/auth/admin/validate", {
            email: email,
          })
            .done(function (data) {})
            .fail(function () {
              alert("Email Already Exist!");
              $("#email").val("");
            });
        }
      }
    }, 1000);
  });

  // by default the installment plan is hidden
  $("#installment").hide();

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

  if (sessionStorage.getItem("type" === "customer")) {
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
        window.location.href = "http://localhost:8080/registration_success";
      })
      .fail(function () {
        alert("Registration failed! Please try again");
      });
  }

  if (sessionStorage.getItem("type") === "admin") {
    const email = $("#email").val();
    const password = $("#password").val();

    $.ajax({
      url: "http://localhost:8080/auth/admin/register",
      method: "POST",
      data: JSON.stringify({
        email: email,
        password: password,
      }),
      contentType: "application/json",
    })
      .done(function () {
        alert("Registration successful");
        window.location.href = "http://localhost:8080/admin_login";
      })
      .fail(function () {
        alert("Registration failed! Please try again");
      });
  }
});
