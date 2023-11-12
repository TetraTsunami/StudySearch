<script>
    export let id;
    export let short_members;
    export let pros;
    export let cons;
</script>

<a class="group-listing group-listing-nonempty" href="/main/landing/specific-gid/{id}">
    <div class="group-listing-content">
        <strong class="group-members">{short_members}</strong>
        <div class="pro-con-listing">
            {#each pros as pro}<div class="pro-con pro">{pro}</div>{/each}{#each cons as con}<div class="pro-con con">{con}</div>{/each}
        </div>
    </div>
</a>

<style>
    a {
        color: var(--text-color);
        text-decoration: none;
    }

    .group-listing {
        display: inline-block;
        margin: 10px;
        padding: 1rem;
        border-radius: 6px;
        min-height: fit-content;
    }

    .group-listing-content {
        display: grid;
    }

    .group-members {
        font-size: 1.5em;
    }

    .pro-con-listing {
        display: inline-block;
        display: flex;
        flex-wrap: wrap;
    }

    .pro-con {
        border-radius: 200px;
        width: max-content;
        height: max-content;
        padding: 5px;
        margin: 10px;
    }

    .pro {
        background-color: palegreen;
    }

    .con {
        background-color: palevioletred;
    }

    .group-listing-nonempty {
        background-color: var(--accent-color);
    }
</style>
