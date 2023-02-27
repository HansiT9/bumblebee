$(document).ready(function () {
  $("#formView").hide(); // initially hiding form

  let searchParams = new URLSearchParams(window.location.search);
  let param = searchParams.get("type");

  if (param === "brands") {
    $("#heading").text("Brands");
    $("#formHeading").text("Add new Brand");
    $("#heading01").text("Brand Name");
    $("#heading02").text("Brand Logo");
    $("#heading03").text("No of Products");
    $("#heading04").text("No of Categories");
    $("#heading05").hide();
    $("#heading06").hide();
    $("#item05").hide();
    $("#item06").hide();
    getAllBrands(param);
  }

  if (param === "Categories") {
    $("#heading").text("Categories");
    $("#formHeading").text("Add new Category");
    $("#heading01").text("Category Name");
    $("#heading02").text("Category Logo");
    $("#heading03").text("No of Products");
    $("#heading04").text("No of Brands");
    $("#heading05").hide();
    $("#heading06").hide();
    $("#item05").hide();
    $("#item06").hide();
    getAllCategories(param);
  }

  if (param === "products") {
    $("#heading").text("Products");
    $("#formHeading").text("Add new Product");
    $("#heading01").text("Products Name");
    $("#heading02").text("Products Logo");
    $("#heading03").text("No of items Available");
    $("#heading04").text("No of items Sold");
    $("#heading05").text("Brand Name");
    $("#heading06").text("category Name");

    getAllProducts(param);
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
    location.reload();
  });

  let timeout;
  $("#name").on("input", function () {
    clearTimeout(timeout);
    timeout = setTimeout(function () {
      if (param === "brands") {
        const name = $("#name").val();
        const message = "Brand Name Already Exist!";
        validateBrandName(name, message, true);
      }
      if (param === "Categories") {
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
    console.log("In save brand form method 1");

    // Request if form type is brand
    if (param === "brands") {
      console.log("In save brand form method 2");
      saveBrand();
    }
    if (param === "Categories") {
      saveCategory();
    }
    if (param === "products") {
      saveProduct();
    }
  });
});

// Fetch methods to display items
function getAllBrands(param) {
  $(".itemHeadings h3, .itemHeadings div, .item h3, .item div").css(
    "width",
    "20%"
  );
  $.get("http://localhost:8080/brand/all")
    .done(function (data) {
      console.log(data);
      $.get("http://localhost:8080/product/count/product_for_brand")
        .done(function (dataCountProduct) {
          console.log(dataCountProduct);
          $.get("http://localhost:8080/product/count/categorie_for_brand")
            .done(function (dataCountCategory) {
              console.log(dataCountCategory);
              if (data === undefined) {
                $(".itemContainer").append(
                  '<h3 style="text-align: center; color: #46CB8B; margin-top: 15%;">Nothing to Show ...</h3>'
                );
                $(
                  ".itemHeadings h3, .itemHeadings div, .item h3, .item div"
                ).css("width", "20%");
              } else {
                $.each(data, function (index, value) {
                  var brandName = value.brandName;
                  var logoUrl = value.brandLogo; // Replace with actual logo URL
                  var countProduct;
                  var countCategory;
                  if (dataCountCategory === undefined) {
                    countCategory = 0;
                  } else {
                    countCategory = dataCountCategory[brandName] || 0;
                  }

                  if (dataCountProduct === undefined) {
                    countProduct = 0;
                  } else {
                    countProduct = dataCountProduct[brandName] || 0;
                  }

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
function getAllCategories(param) {
  $.get("http://localhost:8080/category/all")
    .done(function (data) {
      console.log(data);
      $.get("http://localhost:8080/product/count/product_for_category")
        .done(function (dataCountProduct) {
          console.log(dataCountProduct);
          $.get("http://localhost:8080/product/count/brand_for_category")
            .done(function (dataCountBrand) {
              console.log(dataCountBrand);
              if (data === undefined) {
                $(".itemContainer").append(
                  '<h3 style="text-align: center; color: #46CB8B; margin-top: 15%;">Nothing to Show ...</h3>'
                );
                $(
                  ".itemHeadings h3, .itemHeadings div, .item h3, .item div"
                ).css("width", "20%");
              } else {
                $.each(data, function (index, value) {
                  var categoryName = value.categoryName;
                  var categoryUrl = value.categoryLogo;
                  var countProduct;
                  var countBrand;
                  if (dataCountProduct === undefined) {
                    countProduct = 0;
                  } else {
                    countProduct = dataCountProduct[categoryName] || 0;
                  }

                  if (dataCountBrand === undefined) {
                    countBrand = 0;
                  } else {
                    countBrand = dataCountBrand[categoryName] || 0;
                  }

                  const html =
                    '<div class="item"><h3 id="item01">' +
                    categoryName +
                    '</h3><div id="item02"><img alt="logo" src="' +
                    categoryUrl +
                    '" /></div><h3 id="item03">' +
                    countProduct.toString().padStart(2, "0") +
                    '</h3><h3 id="item04">' +
                    countBrand.toString().padStart(2, "0") +
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
                });
                $(
                  ".itemHeadings h3, .itemHeadings div, .item h3, .item div"
                ).css("width", "20%");

                $(".delete-btn").on("click", function (event) {
                  const btnID = event.target.id;

                  const startIndex = btnID.indexOf("Id") + 2;
                  const endIndex = btnID.indexOf("In");
                  const startIndexL = btnID.indexOf("In") + 2;
                  const id = btnID.slice(startIndex, endIndex);
                  const listId = btnID.slice(startIndexL, startIndexL + 2);
                  console.log(listId);
                  const categoryName = data[listId].categoryName;

                  removeItem(id, param, categoryName);
                });
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
function getAllProducts(param) {
  $.get("http://localhost:8080/product/all")
    .done(function (data) {
      if (data === undefined) {
        $(".itemContainer").append(
          '<h3 style="text-align: center; color: #46CB8B; margin-top: 15%;">Nothing to Show ...</h3>'
        );
        $(".itemHeadings h3, .itemHeadings div, .item h3, .item div").css(
          "width",
          "20%"
        );
      } else {
        $.each(data, function (index, value) {
          var productName = value.productName;
          var productUrl = value.productUrl;
          var productQty = value.productQty;
          var itemsSold = 0;
          var brandName = value.productBrandName;
          var categoryName = value.productCategoryName;

          const html =
            '<div class="item"><h3 id="item01">' +
            productName +
            '</h3><div id="item02"><img alt="logo" src="' +
            productUrl +
            '" /></div><h3 id="item03">' +
            productQty.toString().padStart(2, "0") +
            '</h3><h3 id="item04">' +
            itemsSold.toString().padStart(2, "0") +
            '</h3><h3 id="item05">' +
            brandName +
            '</h3><h3 id="item05">' +
            categoryName +
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
        });
        $(".itemHeadings h3, .itemHeadings div, .item h3, .item div").css(
          "width",
          "16%"
        );
        $(".delete-btn").on("click", function (event) {
          const btnID = event.target.id;

          const startIndex = btnID.indexOf("Id") + 2;
          const endIndex = btnID.indexOf("In");
          const startIndexL = btnID.indexOf("In") + 2;
          const id = btnID.slice(startIndex, endIndex);
          const listId = btnID.slice(startIndexL, startIndexL + 2);
          console.log(listId);
          const productName = data[listId].productName;

          removeItem(id, param, productName);
        });
      }
    })
    .fail(function (error) {
      console.log(error);
    });
}

// delete methods to remove items
function removeItem(id, param, brandName) {
  if (param === "brands") {
    if (
      confirm(
        "By clicking ok, you agree to remove all details related to the  brand" +
          brandName +
          " and remove all products that are registered under the brand name"
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
              location.reload();
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
  if (param === "Categories") {
    if (
      confirm(
        "By clicking ok, you agree to remove all details related to the category name " +
          brandName +
          " and remove all products that are registered under the category name"
      )
    ) {
      $.ajax({
        url:
          "http://localhost:8080/product/remove/all_equals_categoryname/" +
          brandName,
        type: "DELETE",
      })
        .done(function (data) {
          console.log(data.status);
          $.ajax({
            url: "http://localhost:8080/category/remove/single/" + id,
            type: "DELETE",
          })
            .done(function (data) {
              console.log("from final request", data.status);
              location.reload();
            })
            .fail(function (error) {
              console.log("error from final request", error.status);
              location.reload();
            });
        })
        .fail(function (error) {
          console.log("error from one before last request", error.status);
        });
    }
  }
  if (param === "products") {
    if (
      confirm(
        "By clicking ok, you agree to remove all details related to the product name " +
          brandName
      )
    ) {
      $.ajax({
        url: "http://localhost:8080/product/remove/single/" + id,
        type: "DELETE",
      })
        .done(function (data) {
          console.log(data);
          location.reload();
        })
        .fail(function (error) {
          console.log(error);
          location.reload();
        });
    }
  }
}

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

// save methods

function saveBrand() {
  const brandName = $("#name").val();
  let brandUrl = $("#url").val();
  console.log("In save brand method");

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
      console.log("In save brand method 2");
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
