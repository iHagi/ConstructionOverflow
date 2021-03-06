const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    projects: '/api/projects',
};

const toString = ({ id, name, location, structure, squaredMeters, complexity, ownedEng, ownedCom }) => {
    let columns = `
    <td>${name}</td>
    <td>${location}</td>
    <td>${structure}</td>
    <td>${squaredMeters}</td>
    <td>${complexity}</td>
`
    columns += ownedEng
        ? '<td><button class="btn btn-danger">Sold out</button></td>'
        : `<td>
            <form id="project-form-auctionize" class="auciton-project-form" data-id=${id} action="/api/projects/add-to-engineer/${id}" method="post">
                <button class="btn btn-info" type="submit">Auctionize</button>
            </form>
           </td>`

    columns += ownedCom
        ? '<td><button class="btn btn-danger">Sold out</button></td>'
        : `<td>
            <form id="project-form" class="auciton-project-company" data-id=${id} action="/api/projects/add-to-company/${id}" method="post">
                <button class="btn btn-info" type="submit">Auctionize</button>
            </form>
           </td>`
    return `<tr>${columns}</tr>`
};


fetch(URLS.projects)
    .then(response => response.json())
    .then(projects => {
        let result = '';
        projects.forEach(project => {
            const projectString = toString(project);
            result += projectString;
        });

        $('#projects-table').html(result);

       

        loader.hide();
    });
    
    $('#projects-table').on('submit','.auction-project-form','.auciton-project-company', function (ev) {
        ev.preventDefault();
      
        const url = $(this).attr('action');

        fetch(url, { method: 'post' })
            .then(data => {
                console.log(data);

                window.location = '/projects/auctioneer';
            });

    });

    $.ajaxSetup({
    headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
    }
    });
