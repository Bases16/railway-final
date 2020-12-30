
(function () {
    const onButtonClick = function (evt) {
        const stationId = evt.target.getAttribute('data-station-id')
        console.log('station delete clicked: ' + stationId)

        const xhr = new XMLHttpRequest();

        xhr.addEventListener('load', function () {
            console.log('')
            evt.target.parentElement.remove()
        })

        xhr.timeout = 3000
        // xhr.open('DELETE', 'https://jsonplaceholder.typicode.com/posts/' + stationId)
        xhr.open('DELETE', 'http://localhost:8080/rest/stations/500')
        xhr.send()
    }
    const buttons = document.querySelectorAll('[data-station-id]')
    for (let butt of buttons) butt.addEventListener('click', onButtonClick)


/////////////////////////////////////////////////////////////////////////

    const thatButton = document.getElementById('suk')
    thatButton.addEventListener('click', () => {
        const xhr = new XMLHttpRequest();
        xhr.addEventListener('load', () => {
            console.log(xhr.responseText)
        })
        xhr.open('GET', 'http://localhost:8080/rest/stations')
        xhr.send()
    })


})()