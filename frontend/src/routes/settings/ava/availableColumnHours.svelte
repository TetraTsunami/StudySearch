<script>
    import AvailableSelector from "./availableSelector.svelte";
    export let header = "";
    export let isDragging = false;
    export let numHours = 0;
    export let startingHour = 6;
    let selected = [];
    for (let i = startingHour; i < startingHour + numHours; i++) {
        selected.push(i);
    }
</script>

<div class="available-column">
    <div class="header-padding" />
    {#each selected as hour}
        <p class="hour">{hour % 12 + ":00"}</p>
    {/each}
</div>

<style>
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
    .header-padding {
        height: 1.2rem;
    }
    p {
        display: block;
        font-size: 0.8rem;
        height: 0.8rem;
        margin: 0.4rem 0;
    }
</style>
