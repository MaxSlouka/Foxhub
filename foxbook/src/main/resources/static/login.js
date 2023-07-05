const newPostForm = document.querySelector("form");
const message = document.querySelector(".message");

newPostForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const task = Object.fromEntries(formData);
    console.log(task);

    fetch("/api/v1/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    })
        .then(response => {
            if (response.ok) {
                console.log("Response status: " + response.status);
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
