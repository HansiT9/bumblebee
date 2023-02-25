$(document).ready(function () {
  $("#formView").hide();

  // Global variable for which type of form is opened;
  let formType = "n/a";

  // Attach a click event handler to the button
  $("#closeBtn").click(function () {
    formType = "n/a";
    // Hide the div element with a fade effect
    $("#formView").fadeOut();
  });

  let formHeading = $("#heading");
  let nameLabel = $("#nameL");
  let urlLabel = $("#urlL");
  let nameInput = $("#name");
  let q = $("#q");

  $("#newBrand").click(function () {
    formType = "brand";
    $("#formView").show();
    $("#pbn").hide();
    $("#pcn").hide();
    q.hide();
    formHeading.text("Add new Brand");
    nameLabel.text("Brand Name:");
    urlLabel.text("Brand Logo:");
    nameInput.attr("placeholder", "Enter brand name");
  });

  $("#newCat").click(function () {
    formType = "category";
    $("#formView").show();
    $("#pbn").hide();
    $("#pcn").hide();
    q.hide();
    formHeading.text("Add new Category");
    nameLabel.text("Category Name:");
    urlLabel.text("Category Logo:");
    nameInput.attr("placeholder", "Enter category name");
  });

  $("#newProd").click(function () {
    formType = "product";
    $("#formView").show();
    q.show();
    $("#pbn").show();
    $("#pcn").show();
    formHeading.text("Add new Product");
    nameLabel.text("Product Name:");
    urlLabel.text("Product Logo:");
    nameInput.attr("placeholder", "Enter product name");
  });

  // validate brand name if form type is brand
  if (formType === "brand") {
    validateBrandName();
  }

  // form submit
  $("#form-reg").submit(function (event) {
    event.preventDefault();

    // Request if form type is brand
    if (formType === "brand") {
      saveBrand();
    }
  });
});

// validation methods
function validateBrandName() {
  let timeoutName;
  $("#name").on("input", function () {
    const name = $(this).val();
    clearTimeout(timeoutName);
    timeoutName = setTimeout(function () {
      if (name !== "") {
        $.get("http://localhost:8080/brand/name/validate", {
          brandName: name,
        })
          .done(function (data) {
            console.log(data);
          })
          .fail(function () {
            console.log("Brand Name Already Exist!");
            $("#name").val("");
          });
      }
    }, 1000);
  });
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
