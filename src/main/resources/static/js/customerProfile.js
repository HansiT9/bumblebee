$(document).ready(function () {
  $(".loginControl").hide();
  $("#loggedInAs").text("Logged in as " + sessionStorage.getItem("email"));

  if (sessionStorage.getItem("loggedIn") === true) {
    $(".loggedInControl").show();
  }

  if (sessionStorage.getItem("loggedIn") === "n/a") {
    location.href = "http://localhost:8080/admin_login";
  }

  $(".logout").click(function () {
    console.log("logged out");
    sessionStorage.setItem("loggedIn", "n/a");
    window.history.replaceState(null, null, "http://localhost:8080/");
    location.href = window.location;
  });

  let searchParams = new URLSearchParams(window.location.search);
  let param = searchParams.get("cusID");

  $.get("http://localhost:8080/customer/single?cusID=" + param)
    .done(function (data) {
      console.log(data);
      $("#fName").text(data.fullName);
      $("#dob").text(data.dob);
      $("#nic").text(data.nic);

      const f = new Intl.NumberFormat("si-LK", {
        currency: "LKR",
        style: "currency",
      });

      const formattedValueLb = f.format(data.loanBalance);
      const formattedValueUa = f.format(data.usedAmount);

      $("#loanB").text(formattedValueLb);
      $("#usedA").text(formattedValueUa);

      let instalmentField = $("#instalmentP");

      if (data.installmentPlan === "m3") instalmentField.text("03 Months");
      if (data.installmentPlan === "m6") instalmentField.text("06 Months");
      if (data.installmentPlan === "m12") instalmentField.text("12 Months");
      if (data.installmentPlan === "n/a") instalmentField.text("N/A");

      const link = $("<a>");
      link.attr("href", "mailto:" + data.email);
      link.text(data.email);
      $("#email").append(link);
    })
    .fail(function (error) {
      console.log(error);
    });
});
