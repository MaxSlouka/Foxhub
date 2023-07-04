document.addEventListener("DOMContentLoaded", () => {

    const form = document.querySelector('form');

    form.addEventListener('submit', (e) => {
        e.preventDefault(); // Prevent the default form submission

        // Get the nickname / id from the URL or how to handle this?
        const nickname = window.location.pathname.split('/').pop();

        const formData = new FormData(e.target);
        const updatedUser = Object.fromEntries(formData);

        fetch(`/people/${nickname}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedUser)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});
