const newPostForm = document.querySelector("form");

newPostForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(newPostForm);
    const data = Object.fromEntries(formData);

    await fetch("/api/v1/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log("Response status: " + response.status);
                return response.json();
            } else {
                throw new Error("Email already exists!");
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