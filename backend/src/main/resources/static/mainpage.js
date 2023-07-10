const profileLink = document.querySelector('nav a:nth-child(1)');
const homeLink = document.querySelector('nav a:nth-child(2)');
const loginLink = document.querySelector('nav a:nth-child(4)');
const groupLink = document.querySelector('nav a:nth-child(5)');

profileLink.addEventListener('click', () => {
    console.log('Profile link clicked');
});

homeLink.addEventListener('click', () => {
    console.log('Home link clicked');
});

loginLink.addEventListener('click', () => {
    console.log('Login link clicked');
});

groupLink.addEventListener('click', () => {
    console.log('Group link clicked');
});

function searchAllPeople() {
    console.log('Searching all people...');
}

const searchPeopleLink = document.querySelector('.main-aside a');

searchPeopleLink.addEventListener('click', searchAllPeople);
