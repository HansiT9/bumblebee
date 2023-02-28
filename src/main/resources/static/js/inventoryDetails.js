$(document).ready(function () {
  $("#formView").hide();
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

  // Global variable for which type of form is opened;
  let formType = "n/a";

  // Attach a click event handler to the button
  $("#closeBtn").click(function () {
    formType = "n/a";
    // Hide the div element with a fade effect
    $("#form-reg")[0].reset();
    $("#formView").fadeOut();
  });

  let formHeading = $("#heading");
  let nameLabel = $("#nameL");
  let urlLabel = $("#urlL");
  let nameInput = $("#name");
  let q = $("#q");

  $("#newBrand").click(function () {
    formType = "brand";
    console.log(formType);
    $("#formView").show();
    $("#pbn").hide();
    $("#pcn").hide();
    q.hide();
    formHeading.text("Add new Brand");
    nameLabel.text("Brand Name:");
    urlLabel.text("Brand Logo:");
    nameInput.attr("placeholder", "Enter brand name");
    $("#quantity").prop("required", false);
    $("#category").prop("required", false);
    $("#brand").prop("required", false);
  });

  $("#newCat").click(function () {
    formType = "category";
    console.log(formType);
    $("#formView").show();
    $("#pbn").hide();
    $("#pcn").hide();
    q.hide();
    formHeading.text("Add new Category");
    nameLabel.text("Category Name:");
    urlLabel.text("Category Logo:");
    nameInput.attr("placeholder", "Enter category name");
    $("#quantity").prop("required", false);
    $("#category").prop("required", false);
    $("#brand").prop("required", false);
  });

  $("#newProd").click(function () {
    formType = "product";
    console.log(formType);
    $("#formView").show();
    q.show();
    $("#pbn").show();
    $("#pcn").show();
    formHeading.text("Add new Product");
    nameLabel.text("Product Name:");
    urlLabel.text("Product Logo:");
    nameInput.attr("placeholder", "Enter product name");
    $("#quantity").prop("required", true);
    $("#category").prop("required", true);
    $("#brand").prop("required", true);
  });

  let timeout;
  $("#name").on("input", function () {
    clearTimeout(timeout);
    timeout = setTimeout(function () {
      if (formType === "brand") {
        const name = $("#name").val();
        const message = "Brand Name Already Exist!";
        validateBrandName(name, message, true);
      }
      if (formType === "category") {
        const name = $("#name").val();
        const message = "Category Name Already Exist!";
        validateCategoryName(name, message, true);
      }
    }, 1000);
  });

  let timeoutBName;
  $("#brand").on("input", function () {
    clearTimeout(timeoutBName);
    timeoutBName = setTimeout(function () {
      const name = $("#brand").val();
      const message = "Brand Name Valid!";
      validateBrandName(name, message, false);
    }, 1000);
  });

  let timeoutCName;
  $("#category").on("input", function () {
    clearTimeout(timeoutCName);
    timeoutCName = setTimeout(function () {
      const name = $("#category").val();
      const message = "Category Name Valid!";
      validateCategoryName(name, message, false);
    }, 1000);
  });

  // form submit
  $("#form-reg").submit(function (event) {
    event.preventDefault();

    // Request if form type is brand
    if (formType === "brand") {
      saveBrand();
    }
    if (formType === "category") {
      saveCategory();
    }
    if (formType === "product") {
      saveProduct();
    }
  });
});

// validation methods
function validateBrandName(name, message, needFormat) {
  if (name !== "") {
    $.get("http://localhost:8080/brand/name/validate", {
      brandName: name,
    })
      .done(function (data) {
        console.log("result", data);
        if (!needFormat) {
          alert("Brand name not registered!");
        }
        $("#brand").val("");
      })
      .fail(function () {
        alert(message);
        if (needFormat) {
          $("#name").val("");
        }
      });
  }
}

function validateCategoryName(name, message, needFormat) {
  if (name !== "") {
    $.get("http://localhost:8080/category/name/validate", {
      categoryName: name,
    })
      .done(function (data) {
        console.log(data);
        if (!needFormat) {
          alert("Category name not registered!");
        }
        $("#category").val("");
      })
      .fail(function () {
        alert(message);
        if (needFormat) {
          $("#name").val("");
        }
      });
  }
}

// create methods

function saveBrand() {
  const brandName = $("#name").val();
  let brandUrl = $("#url").val();

  if (brandUrl === "") {
    brandUrl = "http://localhost:8080/image/brand.png";
  }

  $.ajax({
    url: "http://localhost:8080/brand/new",
    method: "POST",
    data: JSON.stringify({
      brandName: brandName,
      brandLogo: brandUrl,
    }),
    contentType: "application/json",
  })
    .done(function () {
      alert("New Brand created.");
      $("#form-reg")[0].reset();
    })
    .fail(function () {
      alert(
        "An error occurred while processing your request, please try again later"
      );
    });
}

function saveCategory() {
  const categoryName = $("#name").val();
  let categoryUrl = $("#url").val();

  if (categoryUrl === "") {
    categoryUrl = "http://localhost:8080/image/categories.png";
  }

  $.ajax({
    url: "http://localhost:8080/category/new",
    method: "POST",
    data: JSON.stringify({
      categoryName: categoryName,
      categoryLogo: categoryUrl,
    }),
    contentType: "application/json",
  })
    .done(function () {
      alert("New Category created.");
      $("#form-reg")[0].reset();
    })
    .fail(function () {
      alert(
        "An error occurred while processing your request, please try again later"
      );
    });
}

function saveProduct() {
  const productName = $("#name").val();
  let productUrl = $("#url").val();
  let productBrandName = $("#brand").val();
  let productCategoryName = $("#category").val();
  let productQty = $("#quantity").val();

  if (productUrl === "") {
    productUrl = "http://localhost:8080/image/product-release.png";
  }

  $.ajax({
    url: "http://localhost:8080/product/new",
    method: "POST",
    data: JSON.stringify({
      productName: productName,
      productUrl: productUrl,
      productBrandName: productBrandName,
      productCategoryName: productCategoryName,
      productQty: productQty,
    }),
    contentType: "application/json",
  })
    .done(function () {
      alert("New Product created.");
      $("#form-reg")[0].reset();
    })
    .fail(function () {
      alert(
        "An error occurred while processing your request, please try again later"
      );
    });
}
