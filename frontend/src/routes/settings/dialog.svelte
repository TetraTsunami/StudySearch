<script>
    import { browser } from "$app/environment";
    // get query string
    let query = "";
    if (browser) {
        if (window.location.search !== "") {
            query = window.location.search.substring(1).split("=")[1];
        }
    }
</script>

{#if (query !== "")}
    <div class="dialog">
        <p>{query}</p>
    </div>
{/if}

<style>
    .dialog {
        position: absolute;
        top: 6rem;
        padding: 1rem;
        width: fit-content;
        background-color: var(--accent-color);
        left: 50%;
        right: 50%;
        transform: translate(-50%, -50%);
        border-radius: 0.5rem;
        display: flex;
        justify-content: center;
        align-items: center;
        color: var(--text-color);
    }
</style>
