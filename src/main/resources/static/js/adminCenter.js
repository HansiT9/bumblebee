$(document).ready(function () {
  $(".loginControl").hide();
  $("#loggedInAs").text("Logged in as " + sessionStorage.getItem("email"));

  if (sessionStorage.getItem("loggedIn") === true) {
    $(".loggedInControl").show();
  }

  if (sessionStorage.getItem("loggedIn") === "n/a") {
    console.log("in logged out");
    location.href = "http://localhost:8080/admin_login";
  }

  $(".logout").click(function () {
    console.log("logged out");
    sessionStorage.setItem("loggedIn", "n/a");
    window.history.replaceState(null, null, "http://localhost:8080/");
    location.href = window.location;
  });

  getBrandCount();
  getCustomerCount();
  getCategoryCount();
  getProductCount();
});

$("#refreshCustomer, #refreshBrand, #refreshCategory, #refreshProduct").click(
  function (event) {
    const id = event.target.id;

    if (id === "refreshCustomerIcon") {
      getCustomerCount();
    }
    if (id === "refreshBrandIcon") {
      getBrandCount();
    }
    if (id === "refreshCategoryIcon") {
      getCategoryCount();
    }
    if (id === "refreshProductIcon") {
      getProductCount();
    }
  }
);

function getBrandCount() {
  $.ajax({
    url: "http://localhost:8080/brand/count",
    type: "GET",
    success: function (count) {
      console.log("Number of brands: " + count);
      const link = $("<a>");
      link.attr("href", "http://localhost:8080/inventory_type?type=brands");
      link.text(count.toString().padStart(2, "0"));
      $("#bCount").text("");
      $("#bCount").append(link);
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log("Error: " + textStatus);
    },
  });
}

function getCustomerCount() {
  $.ajax({
    url: "http://localhost:8080/customer/count",
    type: "GET",
    success: function (count) {
      console.log("Number of Customer: " + count);
      const link = $("<a>");
      link.attr("href", "http://localhost:8080/customer_details");
      link.text(count.toString().padStart(2, "0"));
      $("#cusCount").text("");
      $("#cusCount").append(link);
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log("Error: " + textStatus);
    },
  });
}

function getCategoryCount() {
  $.ajax({
    url: "http://localhost:8080/category/count",
    type: "GET",
    success: function (count) {
      console.log("Number of Category: " + count);
      const link = $("<a>");
      link.attr("href", "http://localhost:8080/inventory_type?type=Categories");
      link.text(count.toString().padStart(2, "0"));
      $("#cateCount").text("");
      $("#cateCount").append(link);
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log("Error: " + textStatus);
    },
  });
}

function getProductCount() {
  $.ajax({
    url: "http://localhost:8080/product/count",
    type: "GET",
    success: function (count) {
      console.log("Number of Product: " + count);
      const link = $("<a>");
      link.attr("href", "http://localhost:8080/inventory_type?type=products");
      link.text(count.toString().padStart(2, "0"));
      $("#pCount").text("");
      $("#pCount").append(link);
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log("Error: " + textStatus);
    },
  });
}
