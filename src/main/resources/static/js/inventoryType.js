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
    getAllBrands();
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

    getAllCategories();
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

    getAllProducts();
  }

  $("#refresh").click(function () {
    location.reload();
  });

  $("#new").click(function () {
    $("#formView").show();

    let nameLabel = $("#nameL");
    let urlLabel = $("#urlL");
    let nameInput = $("#name");
    let q = $("#q");

    if (param === "brands") {
      $("#pbn").hide();
      $("#pcn").hide();
      q.hide();
      nameLabel.text("Brand Name:");
      urlLabel.text("Brand Logo:");
      nameInput.attr("placeholder", "Enter brand name");
    }

    if (param === "Categories") {
      $("#pbn").hide();
      $("#pcn").hide();
      q.hide();
      nameLabel.text("Category Name:");
      urlLabel.text("Category Logo:");
      nameInput.attr("placeholder", "Enter category name");
    }

    if (param === "products") {
      $("#pbn").show();
      $("#pcn").show();
      q.show();
      nameLabel.text("Product Name:");
      urlLabel.text("Product Logo:");
      nameInput.attr("placeholder", "Enter product name");
    }
  });

  $("#closeBtn").click(function () {
    $("#formView").fadeOut();
  });
});

// Fetch methods to display items
function getAllBrands() {
  $.get("http://localhost:8080/brand/all")
    .done(function (data) {
      console.log(data);
      $.get("http://localhost:8080/product/count/product_for_brand")
        .done(function (dataCountProduct) {
          console.log(dataCountProduct);
          $.get("http://localhost:8080/product/count/categorie_for_brand")
            .done(function (dataCountCategory) {
              console.log(dataCountCategory);
              if (data.length === 0) {
                $(".itemContainer").append(
                  '<h3 style="text-align: center; color: #46CB8B; margin-top: 10px;">Nothing to Show</h3>'
                );
              } else {
                $.each(data, function (index, value) {
                  var brandName = value.brandName;
                  var logoUrl = value.brandLogo; // Replace with actual logo URL
                  var countProduct = dataCountProduct[brandName] || 0;
                  var countCategory = dataCountCategory[brandName] || 0;
                  const html =
                    '<div class="item"><h3 id="item01">' +
                    brandName +
                    '</h3><div id="item02"><img alt="logo" src="' +
                    logoUrl +
                    '" /></div><h3 id="item03">' +
                    countProduct.toString().padStart(2, "0") +
                    '</h3><h3 id="item04">' +
                    countCategory.toString().padStart(2, "0") +
                    '</h3><div class="controlsContainer"><button class="controlBtn delete-btn" id="dId' +
                    value.id +
                    "In" +
                    index +
                    '">Remove<ion-icon name="trash-outline"></ion-icon></button><button class="controlBtn update-btn" id="uId' +
                    value.id +
                    "In" +
                    index +
                    '">Update<ion-icon name="refresh-outline"></ion-icon></button></div></div>';
                  $(".itemContainer").append(html);
                  $(
                    ".itemHeadings h3, .itemHeadings div, .item h3, .item div"
                  ).css("width", "20%");
                });

                $(".delete-btn").on("click", function (event) {
                  const btnID = event.target.id;

                  const startIndex = btnID.indexOf("Id") + 2;
                  const endIndex = btnID.indexOf("In");
                  const startIndexL = btnID.indexOf("In") + 2;
                  const id = btnID.slice(startIndex, endIndex);
                  const listId = btnID.slice(startIndexL, startIndexL + 2);
                  const brandName = data[listId].brandName;

                  removeItem(id, param, brandName);
                });

                $(".update-btn").on("click", function (event) {});
              }
            })
            .fail(function (error) {
              console.log(error);
            });
        })
        .fail(function (error) {
          console.log(error);
        });
    })
    .fail(function (error) {
      console.log(error);
    });
}
function getAllCategories() {}
function getAllProducts() {}

// delete methods to remove items
function removeItem(id, param, brandName) {
  if (param === "brands") {
    if (
      confirm(
        "By clicking ok, you agree to remove all details related brand and remove all products that are registered under the brand name"
      )
    ) {
      $.ajax({
        url:
          "http://localhost:8080/product/remove/all_equals_brandname/" +
          brandName,
        type: "DELETE",
      })
        .done(function (data) {
          console.log(data);
          $.ajax({
            url: "http://localhost:8080/brand/remove/single/" + id,
            type: "DELETE",
          })
            .done(function (data) {
              console.log("from final request", data);
            })
            .fail(function (error) {
              console.log("error from final request", error);
              location.reload();
            });
        })
        .fail(function (error) {
          console.log(error);
        });
    }
  }
  if (type === "category") {
  }
  if (type === "product") {
  }
}
