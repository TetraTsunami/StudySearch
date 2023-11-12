<script>
    export let isDragging = false;
    export let value = false;
    export let coordinate = [0, 0];
</script>

<!-- svelte-ignore a11y-no-static-element-interactions-->
<!-- svelte-ignore a11y-click-events-have-key-events -->
<div class={value ? "active" : ""}
     on:mouseenter={() => {if (isDragging) value = !value}}
     on:mousedown={() => {value = !value
     }}
/>

<style>
    div {
        width: 100%;
        flex-grow: 1;
    }
    .active {
        background-color: #f62c2c;
    }
</style>
