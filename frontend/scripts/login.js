$( "#loginForm" ).submit(function( event ) {
  event.preventDefault();

    const email = $(this).find('[name=email]').val()
    const password = $(this).find('[name=password]').val()

    $.ajax({
    type: "POST",
    url: "https://do-to-list-jee.herokuapp.com/api/login",
    // The key needs to match your method's input parameter (case-sensitive).
    data: JSON.stringify({ email,password }),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    statusCode: {
        200: function (res) {
            console.log('Success',res)
        },
        401: function (res) {
             console.log('Error', res)
        alert( "Wrong email or password" );
        }
    }
    }).fail(function(error) {
        console.log(error)
        alert('Error occured during login.')
    })

});