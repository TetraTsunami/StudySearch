<script>
    import AvailableColumnHours from "./availableColumnHours.svelte";

    export let grid = [14 * 2, 7];

    $: col = `repeat(${grid[1]}, 1fr)`;
    $: row = `repeat(${grid[0]}, 1fr)`;
    $: is_active = Array(grid[0]).fill(0).map(_ => Array(grid[1]).fill(false));
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
            adding = !is_active[i][j];
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
        is_active = is_active.map(
            (a, i) => a.map((_, j) => (
                adding ? withinBounds([i, j], hover_end) || is_active[i][j] :
                    !withinBounds([i, j], hover_end) && is_active[i][j]
            )));
    }

    function update_preview() {
        preview = is_active.map(
            (a, i) => a.map((_, j) => (
                adding ? withinBounds([i, j], hover_end) || is_active[i][j] :
                    !withinBounds([i, j], hover_end) && is_active[i][j]
            )));
    }


</script>
<div class="calendar">
    <AvailableColumnHours startingHour={7} numHours={14} />
    <div class="container" style="grid-template-rows: {row}; grid-template-columns: {col};">
        {#each {length: grid[0]} as _, i}
            {#each {length: grid[1]} as k, j}
                <div class:active={preview[i][j]}
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
        width: 15rem;
        grid-gap: 1px;
        background: #999;
    }

    .container div {
        background: #fff;
        height: 1rem;
    }

    div.active {
        background: orange;
    }
</style>
