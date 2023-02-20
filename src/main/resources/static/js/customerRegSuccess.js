$(document).ready(function () {
  // start the countdown and update the display every second
  const countdownInterval = setInterval(updateCountdown, 1000);
  let count = 5; // set the countdown time
  const countdownElem = $("#secTimer"); // get the countdown element

  // function to update the countdown display
  function updateCountdown() {
    countdownElem.text(count + " seconds"); // update the countdown display
    console.log(count);
    count--; // decrement the count
    if (count < 0) {
      clearInterval(countdownInterval); // stop the countdown when count reaches 0
      window.location.href = "http://localhost:8080/";
    }
  }
});
