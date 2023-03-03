$(document).ready(function () {
  if (sessionStorage.getItem("loggedIn") === true) {
    location.href = "http://localhost:8080/admin_center";
  }

  // default view
  $("#formCustomer").hide();
  $("#fullName, #dob, #email, #password, #nic").prop("required", false);
  $("#emailAdmin, #passwordAdmin").prop("required", true);
  sessionStorage.setItem("type", "admin");

  // switch between admin and customer register
  $("#switchOption").change(function () {
    if ($(this).is(":checked")) {
      $("#formCustomer").show();
      $("#formAdmin").hide();
      $("#fullName, #dob, #email, #password, #nic").prop("required", true);
      $("#emailAdmin, #passwordAdmin").prop("required", false);

      sessionStorage.setItem("type", "customer");
    } else {
      $("#formCustomer").hide();
      $("#formAdmin").show();
      $("#fullName, #dob, #email, #password, #nic").prop("required", false);
      $("#emailAdmin, #passwordAdmin").prop("required", true);
      sessionStorage.setItem("type", "admin");
    }
  });

  // Validate email with database
  let timeoutIdEmail;
  $("#email").on("input", function () {
    console.log("in customer email validation");
    const email = $(this).val();
    const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    clearTimeout(timeoutIdEmail);
    timeoutIdEmail = setTimeout(function () {
      if (email !== "" && patternEmail.test(email)) {
        if (sessionStorage.getItem("type") === "customer") {
          $.get("http://localhost:8080/auth/customer/email/validate", {
            email: email,
          })
            .done(function (data) {})
            .fail(function () {
              alert("Customer Email Already Exist!");
              $("#email").val("");
            });
        }
      }
    }, 1000);
  });

  let timeoutIdAdmin;
  $("#emailAdmin").on("input", function () {
    console.log("in Admin email validation");
    const email = $(this).val();
    const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    clearTimeout(timeoutIdAdmin);
    timeoutIdAdmin = setTimeout(function () {
      if (email !== "" && patternEmail.test(email)) {
        if (sessionStorage.getItem("type") === "admin") {
          $.get("http://localhost:8080/auth/admin/email/validate", {
            email: email,
          })
            .done(function (data) {})
            .fail(function () {
              alert("Admin email Already Exist!");
              $("#emailAdmin").val("");
            });
        }
      }
    }, 1000);
  });

  let timeOutNicCustomer;
  $("#nic").on("input", function () {
    const nic = $(this).val();
    const patternId = /^([0-9]{9}[x|X|v|V]|[0-9]{12})$/;
    clearTimeout(timeOutNicCustomer);
    timeOutNicCustomer = setTimeout(function () {
      if (nic !== "" && patternId.test(nic)) {
        if (sessionStorage.getItem("type") === "customer") {
          console.log(nic);
          $.get("http://localhost:8080/auth/customer/nic/validate/" + nic)
            .done(function (data) {
              console.log(data);
            })
            .fail(function () {
              alert("Nic Already Registered!");
              $("#nic").val("");
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
          $("#nicGroup").hide();
        } else {
          $("#installment").show();
          $("#nicGroup").show();
        }
      }
    }, 1000);
  });
});

$("#form-reg").submit(function (event) {
  event.preventDefault();

  const type = sessionStorage.getItem("type");
  console.log(type);

  if (type === "customer") {
    console.log("in customer register");
    const fullName = $("#fullName").val();
    const dob = $("#dob").val();
    const email = $("#email").val();
    const password = $("#password").val();
    let nic = $("#nic").val();
    let $checkedRadio = $("input[type=radio]:checked");

    let installmentPlan = $checkedRadio.attr("id");
    if (installmentPlan === undefined) {
      installmentPlan = "n/a";
    }
    if (nic === undefined) {
      nic = "n/a";
    }

    console.log("in customer register", nic);

    $.ajax({
      url: "http://localhost:8080/auth/customer/register",
      method: "POST",
      data: JSON.stringify({
        customerFullName: fullName,
        dob,
        nic,
        customerEmail: email,
        password,
        installmentPlan: installmentPlan,
      }),
      contentType: "application/json",
    })
      .done(function () {
        alert("Customer registration successful");
        window.location.href = "http://localhost:8080/registration_success";
      })
      .fail(function () {
        alert("Registration failed! Please try again");
      });
  }

  if (type === "admin") {
    const email = $("#emailAdmin").val();
    const password = $("#passwordAdmin ").val();

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
        alert("Admin registration successful");
        window.location.href = "http://localhost:8080/admin_login";
      })
      .fail(function () {
        alert("Registration failed! Please try again");
      });
  }
});
