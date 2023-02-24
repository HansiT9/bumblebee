$(document).ready(function () {
  $("#formView").hide();

  // Attach a click event handler to the button
  $("#closeBtn").click(function () {
    // Hide the div element with a fade effect
    $("#formView").slideUp();
  });

  let nameLabel = $("#nameL");
  let urlLabel = $("#urlL");

  $("#newBrand").click(function () {
    $("#formView").slideDown();
    $("#pbn").hide();
    $("#pcn").hide();
    nameLabel.text("Brand Name:");
    urlLabel.text("Brand Logo:");
  });

  $("#newCat").click(function () {
    $("#formView").slideDown();
    $("#pbn").hide();
    $("#pcn").hide();
    nameLabel.text("Category Name:");
    urlLabel.text("Category Logo:");
  });

  $("#newProd").click(function () {
    $("#formView").slideDown();
    $("#pbn").show();
    $("#pcn").show();
    nameLabel.text("Product Name:");
    urlLabel.text("Product Logo:");
  });
});
