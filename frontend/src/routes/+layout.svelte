<script>
    import Header from './header.svelte';
</script>

<div id="container" style="--accent-color: #e3ba42">
    <Header />
    <slot />
</div>

<style>
    #container {
        min-height: calc(100vh);
        display: flex;
        position: relative;
        flex-direction: column;
    }
</style>
