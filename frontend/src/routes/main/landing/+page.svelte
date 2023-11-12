<script lang="ts">
    import { onMount } from "svelte";
    import SmallGroup from "./smallGroup.svelte";

    import { page } from '$app/stores';

    let missing = [];
    onMount(async () => {
        if ($page.url.searchParams.has('idontcare')) {
            return;
        }
        let fetched_missing_data = await fetch("/api/user/settings/missing");
        missing = await fetched_missing_data.json();
        if (missing.length !== 0) {
            document.location = "/settings";
        }
    });

    type Group = {
        "id": number,
        "short_members": string,
        "pros": string[],
        "cons": string[]
    };
    type MoreMarker = {
        "special": "more"
    }
    let groups_by_class: Record<string, (Group | MoreMarker)[]> = {
        "COMP SCI 300": [
            {
                "id": 111,
                "short_members": "Joe, Bob, and Your Mom",
                "pros": ["Same Section"],
                "cons": []
            },
            {
                "id": 222,
                "short_members": "The Wither and The Ender Dragon",
                "pros": [],
                "cons": []
            },
            {
                "id": 333,
                "short_members": "Wallace and Grommit",
                "pros": [],
                "cons": ["May Be Too Small"]
            },
            {
                "special": "more",
            }
        ],
        "MATH 234": [],
        "ASTRON 200": [
            {
                "id": 101,
                "short_members": "Neil and Carl",
                "pros": ["Same Section"],
                "cons": ["May Be Too Small"]
            }
        ],
    };
    function load_more(name: string) {
        console.log(`Loading more for ${name}.`);
    }
</script>

<div style="font-size: 0;height: 20px;line-height: 0;"></div>
<div>
    {#each Object.entries(groups_by_class) as [name, groups]}
        <h2><i>{name}</i></h2>
        <div class="groups-for-a-class">
            {#each groups as obj}
                {#if "special" in obj}
                    <div class="group-listing group-listing-more" on:click={() => {
                        load_more(name);
                    }}>
                        <strong>Load more...</strong>
                    </div>
                {:else}
                    {#each [obj] as {id, short_members, pros, cons}}
                        <SmallGroup {id} {short_members} {pros} {cons}/>
                    {/each}
                {/if}
            {:else}
                <div class="group-listing group-listing-empty">
                    <p>No groups available, please check back later. 😢</p>
                </div>
            {/each}
        </div>
        {#if name !== Object.keys(groups_by_class).at(-1)}
            <hr/>
        {/if}
    {/each}
</div>



<style>
    .groups-for-a-class {
        display: flex;
        justify-content: center;
    }

    .group-listing {
        display: inline-block;
        margin: 10px;
        padding: 5px;
        border-radius: 6px;
    }

    .group-listing-empty {
        background-color: lightgrey;
    }

    .group-listing-more {
        background-color: lightgrey;
        font-size: 20pt;
    }

    hr {
        margin: 20px;
    }
</style>
