<script>
    let menuItems = [
        { id: 1, name: 'Dashboard', faIcon: "fas fa-chart-area"},
        { id: 2, name: 'Query', faIcon: "fas fa-search" },
        { id: 3, name: 'Configuration', faIcon: "fas fa-sliders-h"},
    ];
    export let selectedMenu = 1;
</script>

<ul class="menu">
    {#each menuItems as item}
        <li on:mouseup="{e => {selectedMenu = item.id;}}">
            <i class="icon {item.faIcon}"></i>
            <span>{item.name}</span>
        </li>
    {/each}
</ul>

<style>
    ul {
        background-color: var(--colB);
        color: var(--colZ);
        list-style: none;
        margin: 0;
        padding: 20px 0 20px 0;
        display: flex;
        flex-direction: column;
        width: 100px;
    }
    ul li {
        height: 64px;
        width: 80px;
        margin-left: calc(50px - 45px);
        text-align:center;
        cursor: pointer;
        border: 1px solid rgba(255, 255, 255, 0.0);
        margin-bottom: 10px;
        padding: 4px 5px 0px 5px;
    }
    ul li:hover {
        background-color: rgba(255, 255, 255, 0.2);
        border: 1px solid rgba(255, 255, 255, 0.5);
    }
    ul li .icon {
        font-size: 24px;
        height: 24px;
        width: 24px;
        padding-top: 5px;
    }
    ul li span {
        display:block;
        padding-top: 5px;
        font-size: 12px;
    }
</style>
