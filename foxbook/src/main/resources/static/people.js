fetch('/api/people')
    .then(response => response.json())
    .then(data => {
        data.forEach(person => {
            const li = document.createElement('li');
            li.innerHTML = `${person.firstName} ${person.lastName} (${person.email})`;
            document.querySelector('.list-of-people').appendChild(li);
        });
    });