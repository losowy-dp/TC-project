function fun_checkbox() {
    var checkbox = document.getElementById('cb');
    var checkbox_label = document.getElementById('cb_label');
    if (checkbox_label.textContent == "Tak") {
      checkbox_label.textContent = "Nie";
      $('#block_terminy').css('display','none');
      $('.block_2').css('height','14%');
    } else {
      checkbox_label.textContent = "Tak";
      $('#block_terminy').css('display','flex');
      $('.block_2').css('height','49%')
    }
  }