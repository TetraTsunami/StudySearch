<script>
    import AvailableColumnHours from "./availableColumnHours.svelte";
    export let subHours = 2;
    export let startingHour = 7;
    export let numHours = 16;

    export let grid = [numHours * subHours, 7];
    let gridHeadings = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

    let col = `repeat(${grid[1]}, 1fr)`;
    let row = `repeat(${grid[0]}, 1fr)`;
    export let active_cells = Array(grid[0]).fill(0).map(_ => Array(grid[1]).fill(false));
    $: active_cells = Array(grid[0]).fill(0).map(_ => Array(grid[1]).fill(false));
    $: preview = Array(grid[0]).fill(0).map(_ => Array(grid[1]).fill(false));

    let start = [];
    let end = [];
    let hover_end = []
    let clicked = false;
    let adding = true;


    function select(i, j) {
        if (!clicked) {
            // if the mouse just came down
            start = [i, j];
            hover_end = [i, j];
            adding = !active_cells[i][j];
        } else {
            end = [i, j];
        }

        clicked = !clicked;
        save_active([i, j]);
        update_preview([i, j]);
    }

    function hover(i, j) {
        if (!clicked) return;
        hover_end = [i, j];
        update_preview(hover_end);
    }

    function withinBounds([i, j], [i2, j2]) {
        return ((i - start[0]) * (i - i2) <= 0) &&
            ((j - start[1]) * (j - j2)<= 0)
    }

    function save_active (end) {
        active_cells = active_cells.map(
            (a, i) => a.map((_, j) => (
                adding ? withinBounds([i, j], hover_end) || active_cells[i][j] :
                    !withinBounds([i, j], hover_end) && active_cells[i][j]
            )));
    }

    function update_preview() {
        preview = active_cells.map(
            (a, i) => a.map((_, j) => (
                adding ? withinBounds([i, j], hover_end) || active_cells[i][j] :
                    !withinBounds([i, j], hover_end) && active_cells[i][j]
            )));
    }


</script>
<div class="calendar">
    <AvailableColumnHours {startingHour} {numHours} />
    <div class="container" style="grid-template-rows: {row + 1}; grid-template-columns: {col};">
        {#each gridHeadings as day}
            <div class="section-header">{day}</div>
        {/each}
        {#each {length: grid[0]} as _, i}
            {#each {length: grid[1]} as _, j}
                <div class:active={preview[i][j]}
                     class:hour={i % subHours === 0}
                     class="cell"
                     on:mousedown={() => select(i, j)}
                     on:mouseover={() => hover(i, j)}
                ></div>
            {/each}
        {/each}

    </div>
</div>

<style>
    .calendar {
        display: flex;
        flex-direction: row;
    }
    .container {
        display: grid;
        border: 1px solid #999;
        border-radius: 2px;
        width: 30rem;
        background: #999;
    }

    div.cell {
        border: 1px solid #999;
        background: #fff;
        height: 1rem;
    }

    div.active {
        background: var(--accent-color);
    }

    div.hour {
        border-top: black solid 2px;
    }

    .section-header {
        background: #fff;
        text-align: center;
        font-size: 0.8em;
        height: 2rem;
    }
</style>
