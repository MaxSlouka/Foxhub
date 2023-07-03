
document.addEventListener("DOMContentLoaded", () => {

    const newPostForm = document.querySelector("form");
    const message = document.querySelector(".message");
    message.textContent = "";

    newPostForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const formData = new FormData(event.target);
        const task = Object.fromEntries(formData);

        fetch("/authentication", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error occurred!");
                }
            })
            .then(data => {
                console.log("Provided data: ");
                console.log(data);
            })
            .catch(error => {
                console.error("Error:", error);
            });

        newPostForm.reset();
    });
});