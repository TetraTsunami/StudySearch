<script>
    import Header from './header.svelte';
</script>

<Header />
<main id="container" style="--accent-color: #e3ba42">
    <div class="header-padding" />
    <slot />
</main>

<style>
    :global(*) {
        box-sizing: border-box;
        margin: 0;
    }
    :global(html, body) {
        font-family: "Wotfard",Futura, -apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    }
    :root {
        --accent-color: #e1d1fa;
        --bg-primary: #ffffff;
        --text-color: #000000;
    }
    :global(body) {
        background: var(--bg-primary);
        color: var(--text-color);
    }
    main {
        max-width: 1500px;
        margin: auto;
        padding: 0 1rem;
    }
    #container {
        min-height: calc(100vh);
        display: flex;
        position: relative;
        flex-direction: column;
    }
    .header-padding {
        height: 4rem;
    }
</style>
