<script>
    import AvailableColumnHours from "./availableColumnHours.svelte";
    import AvailableSelector from "./availableSelector.svelte";

    let isDragging = false;
    let startingHour = 7;
    let numHours = 14;
    let subHours = 2;
    let days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
    let columns = [[], [], [], [], [], [], []];
    for (let day in days) {
        for (let i = 0; i < (numHours) * subHours; i++) {
            columns[day][i] = false;
        }
    }
    $: console.log(columns);
</script>

<!-- svelte-ignore a11y-no-static-element-interactions-->
<div class="available-widget"
     on:mouseleave={() => {isDragging = false}}
     on:mousedown={() => {isDragging = true}}
     on:mouseup={() => {isDragging = false}}>
    <AvailableColumnHours {startingHour} {numHours}/>

    {#each days.entries() as [i, day]}
        <div class="available-column">
            <h1>{day}</h1>

            {#each {length: columns[i].length / 2} as hour}
                <div class="hour-group">
                    <div class={columns[i][hour * 2] ? "active" : ""}
                         on:mouseenter={() => {if (isDragging) columns[i][hour * 2] = !columns[i][hour * 2]}}
                         on:mousedown={() => {columns[i][hour * 2] = !columns[i][hour * 2]
                        }}
                    />
                    <div class={columns[i][hour * 2 + 1] ? "active" : ""}
                         on:mouseenter={() => {if (isDragging) columns[i][hour * 2 + 1] = !columns[i][hour * 2 + 1]}}
                         on:mousedown={() => {columns[i][hour * 2 + 1] = !columns[i][hour * 2 + 1]
                        }}
                    />
                </div>
            {/each}

        </div>
    {/each}

</div>

<style>
    .available-widget {
        display: flex;
        flex-direction: row;
        width: 100%;
        height: 100%;
    }

    .available-column {
        display: flex;
        flex-grow: 1;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        height: 100%;
        width: 5rem;
        border: 1px solid black;
    }

    h1 {
        font-size: 0.9rem;
        margin: 0;
        padding: 0;
    }

    .hour-group {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        width: 100%;
        height: 2rem;
        border: 1px solid greenyellow;
    }

    .active {
        background-color: greenyellow;
    }
</style>
