$(document).ready(function () {
  $("#formView").hide();

  // Attach a click event handler to the button
  $("#closeBtn").click(function () {
    // Hide the div element with a fade effect
    $("#formView").fadeOut();
  });

  let nameLabel = $("#nameL");
  let urlLabel = $("#urlL");
  let nameInput = $("#name");
  let q = $("#q");

  $("#newBrand").click(function () {
    $("#formView").show();
    $("#pbn").hide();
    $("#pcn").hide();
    q.hide();
    nameLabel.text("Brand Name:");
    urlLabel.text("Brand Logo:");
    nameInput.attr("placeholder", "Enter brand name");
  });

  $("#newCat").click(function () {
    $("#formView").show();
    $("#pbn").hide();
    $("#pcn").hide();
    q.hide();
    nameLabel.text("Category Name:");
    urlLabel.text("Category Logo:");
    nameInput.attr("placeholder", "Enter category name");
  });

  $("#newProd").click(function () {
    $("#formView").show();
    q.show();
    $("#pbn").show();
    $("#pcn").show();
    nameLabel.text("Product Name:");
    urlLabel.text("Product Logo:");
    nameInput.attr("placeholder", "Enter product name");
  });
});
