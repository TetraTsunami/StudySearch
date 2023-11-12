<script>
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
        <div class="hour">{((hour - 1) % 12) + 1 + ":00"}</div>
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
        height: 2rem;
    }
    .hour {
        display: flex;
        justify-content: center;
        align-items: center;
        padding-bottom: 0.75rem;
        font-size: 1.2rem;
        height: 2rem;
        width: 100%;
        border-top: 2px solid black;
    }
</style>
