<script>
    import CalendarWidget from "./calendarWidget.svelte";
    let gridget = [];
    let startingHour = 7;

    const sendGrid = () => {
        let grid = gridget;
        // transpose array
        grid = grid[0].map((col, i) => grid.map(row => row[i]));
        // turn into array of objects representing start/end times of freedom
        // 0: [{start: 0, end: 1}, {start: 3, end: 5}], 1: etc
        grid = grid.map((col, i) => {
            let start = -1;
            let end = -1;
            let arr = [];
            col.forEach((cell, j) => {
                if (cell && start == -1) {
                    start = j;
                } else if (!cell && start != -1) {
                    end = j;
                    arr.push({start: indexToTime(start), end: indexToTime(end)});
                    start = -1;
                    end = -1;
                }
            });
            if (start != -1) {
                end = col.length;
                arr.push({start: indexToTime(start), end: indexToTime(end)});
            }
            return arr;
        });
        // send to server
        console.log(grid)
        fetch('/api/user/availability', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(grid)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
    const indexToTime = (index) => {
        let hour = Math.floor(index / 2) + startingHour;
        let minute = (index % 2) * 30;
        return leftPad(hour) + ":" + leftPad(minute)
    }

    // jankiest pad in the West
    const leftPad = (int) => {
        if (int < 10) {
            return "0" + int;
        }
        return int;
    }
</script>

<div class="centered-container">
    <h1 class="page-title"><a class="same-unvisited" href="/settings">Settings</a> / Availability</h1>
</div>
<br/>

<div class="hv-centered-container settings-form">
    <p>Block out days that you're free</p>
    <CalendarWidget {startingHour} bind:active_cells={gridget} />
    <br/>
    <button on:click={sendGrid}>Save</button>
</div>

<style>
    .centered-container {
        display: flex;
        justify-content: center;
    }

    .hv-centered-container {
        position: absolute;
        left: 50%;
        top: 60%;
        transform: translate(-50%, -50%);
    }

    .page-title {
        font-size: 40pt;
    }

    .settings-form {
        border: inset 2px;
        padding: 20px;
        font-size: 18pt;
    }

    a.same-unvisited {
        color:blue;
        text-decoration: none;
    }

    a.same-unvisited:visited {
        color: blue;
    }
</style>
