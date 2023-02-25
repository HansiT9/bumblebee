$(document).ready(function () {
  $("#formView").hide(); // initially hiding form

  let searchParams = new URLSearchParams(window.location.search);
  let param = searchParams.get("type");

  if (param === "brands") {
    $("#heading").text("Brands");
    $("#heading01").text("Brand Name");
    $("#heading02").text("Brand Logo");
    $("#heading03").text("No of Products");
    $("#heading04").text("Available Categories");
    $("#heading05").hide();
    $("#heading06").hide();
    $("#item05").hide();
    $("#item06").hide();
    $(".itemHeadings h3, .itemHeadings div, .item h3, .item div").css(
      "width",
      "20%"
    );
  }

  if (param === "Categories") {
    $("#heading").text("Categories");
    $("#heading01").text("category Name");
    $("#heading02").text("category Logo");
    $("#heading03").text("No of Products");
    $("#heading04").text("No of Brands");
    $("#heading05").hide();
    $("#heading06").hide();
    $("#item05").hide();
    $("#item06").hide();
    $(".itemHeadings h3, .itemHeadings div, .item h3, .item div").css(
      "width",
      "20%"
    );
  }

  if (param === "products") {
    $("#heading").text("Products");
    $("#heading01").text("Products Name");
    $("#heading02").text("Products Logo");
    $("#heading03").text("No of items Available");
    $("#heading04").text("No of items Sold");
    $("#heading05").text("Brand Name");
    $("#heading06").text("category Name");
    $(".itemHeadings h3, .itemHeadings div, .item h3, .item div").css(
      "width",
      "16%"
    );
  }

  $("#refresh").click(function () {
    location.reload();
  });

  $("#new").click(function () {
    $("#formView").show();

    let nameLabel = $("#nameL");
    let urlLabel = $("#urlL");
    let nameInput = $("#name");

    if (param === "brands") {
      $("#pbn").hide();
      $("#pcn").hide();
      nameLabel.text("Brand Name:");
      urlLabel.text("Brand Logo:");
      nameInput.attr("placeholder", "Enter brand name");
    }

    if (param === "Categories") {
      $("#pbn").hide();
      $("#pcn").hide();
      nameLabel.text("Category Name:");
      urlLabel.text("Category Logo:");
      nameInput.attr("placeholder", "Enter category name");
    }

    if (param === "products") {
      $("#pbn").show();
      $("#pcn").show();
      nameLabel.text("Product Name:");
      urlLabel.text("Product Logo:");
      nameInput.attr("placeholder", "Enter product name");
    }
  });

  $("#closeBtn").click(function () {
    $("#formView").fadeOut();
  });
});
