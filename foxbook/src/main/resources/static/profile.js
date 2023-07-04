document.addEventListener("DOMContentLoaded", () => {
    const userId = 1; // need to be declared how do we get the user id???

    function fetchUser(id) {
        const url = `/people/${id}`;

        fetch(url)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error occurred while fetching user.");
                }
            })
            .then((user) => {
                fillUserInfo(user);
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    }

    function fillUserInfo(user) {
        const nameElement = document.querySelector("h1");
        const linksElement = document.querySelector(".links");
        const ageElement = document.querySelector(".age p");
        const locationElement = document.querySelector(".location p");
        const telephoneElement = document.querySelector(".telephone p");
        const emailElement = document.querySelector(".email p");
        const aboutContentElement = document.querySelector(".about p");
        const skillsElement = document.querySelector(".skills");

        nameElement.textContent = `${user.firstName} ${user.lastName}`;

        linksElement.innerHTML = "";

        user.socialMedias.forEach((socialMedia) => {
            const link = document.createElement("a");
            link.href = socialMedia.url;
            link.textContent = socialMedia.name;
            linksElement.appendChild(link);
        });

        ageElement.textContent = user.yearOfBirth;
        locationElement.textContent = user.countryResidence;
        telephoneElement.textContent = user.telephone; // need to be set up in User @Entity (now it's absent)
        emailElement.textContent = user.email;
        aboutContentElement.textContent = user.about; // need to be set up in User @Entity (now it's absent)

        skillsElement.innerHTML = "";

        user.technologies.forEach((technology) => {
            const skill = document.createElement("p");
            skill.textContent = technology.name;
            skillsElement.appendChild(skill);
        });
    }

    fetchUser(userId);
});
