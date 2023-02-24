$(document).ready(function () {
  $.get("http://localhost:8080/customer/all")
    .done(function (data) {
      if (data.length === 0) {
        $(".detailsContainer").append(
          '<h3 style="text-align: center; color: #46CB8B; margin-top: 10px;">Nothing to Show</h3>'
        );
      } else {
        $.each(data, function (key, value) {
          $(".detailsContainer").append(
            '<div class="detailsContainer__before"><h3>Full Name : ' +
              value.fullName +
              '</h3><a href="http://localhost:8080/customer_profile?cusID=' +
              value.id +
              '">More info &#187;</a></div>'
          );
        });
      }
    })
    .fail(function (error) {
      console.log(error);
    });
});
